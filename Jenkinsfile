pipeline {
    agent any

    environment {
        IMAGE_NAME = 'exp9-cicd-app'
        CONTAINER_NAME = 'exp9-app'
        HOST_PORT = '8088'
        CONTAINER_PORT = '8080'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn -B clean test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def imageTag = "${env.IMAGE_NAME}:${env.BUILD_NUMBER}"
                    sh "docker build --tag ${imageTag} --tag ${env.IMAGE_NAME}:latest ."
                }
            }
        }

        stage('Deploy Container') {
            steps {
                script {
                    def existingContainer = sh(
                        script: 'docker ps -aq --filter name=^' + env.CONTAINER_NAME + '$',
                        returnStdout: true
                    ).trim()
                    if (existingContainer) {
                        sh "docker rm -f ${env.CONTAINER_NAME}"
                    }
                    def imageTag = "${env.IMAGE_NAME}:${env.BUILD_NUMBER}"
                    sh "docker run --name ${env.CONTAINER_NAME} --publish ${env.HOST_PORT}:${env.CONTAINER_PORT} --detach ${imageTag}"
                }
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}
