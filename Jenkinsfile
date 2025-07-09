pipeline {
    agent any

    tools {
        maven 'M398'  // Ensure this is configured in Jenkins Global Tools
    }

    environment {
        AZURE_CREDENTIALS = credentials('AZURE_FUNCTION_PUBLISH_PROFILE') // Jenkins secret
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Package') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy to Azure Function') {
            steps {
                // Write publish profile to file
                writeFile file: 'publishProfile.publishSettings', text: "${AZURE_CREDENTIALS}"
                
                // Deploy using Maven plugin or Azure CLI
                sh 'mvn azure-functions:deploy -Pazure'
            }
        }
    }
}
