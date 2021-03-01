def gitInfo= {}
pipeline {
    agent any

    stages {
        stage('Git Checkout') {
        steps{
        echo 'Git Checkout started..'
        script {
           gitInfo = checkout scm
           echo "Git Commit Version: $gitInfo.GIT_COMMIT"
        }
        }
        }
        stage('Build') {
        tools {
                     jdk "jdk14"
                     maven "maven"
               }
            steps {
                echo 'Building..'
                sh 'java -version'
                echo "PATH = ${PATH}"
                sh 'mvn clean install'

            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }

        stage('Docker-Image-Build'){

        steps {
            echo 'Building Docker Image'
            sh 'docker build -t weather-app .'
        }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying.... Running Docker Image'
                sh 'docker run -p 8080:8080 weather-app:latest -d'
            }
        }
    }
}