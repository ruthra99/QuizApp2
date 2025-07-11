pipeline {
    agent any

    tools {
            maven 'local-maven'
        }

    environment {
        IMAGE_NAME = 'quizapp-local'
        CONTAINER_NAME = 'quizapp-container'
    }

    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Docker Container') {
            steps {
                // Remove container if it already exists
                sh 'docker rm -f $CONTAINER_NAME || true'

                // Run the container on host port 8081
                sh 'docker run -d -p 8081:8090 --name $CONTAINER_NAME $IMAGE_NAME'
            }
        }
    }
}
