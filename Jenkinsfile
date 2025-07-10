
pipeline {
    agent any

    tools {
        maven 'M398' // Maven tool from Jenkins global config
    }

    environment {
        AZURE_CREDENTIALS = credentials('AZURE_FUNCTION_PUBLISH_PROFILE') // must be type "Secret Text"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Package') {
            steps {
                sh 'mvn clean package azure-functions:package'
            }
        }

        stage('Deploy to Azure Function') {
            steps {
                // Write the secret text content directly to file without interpolation
                script {
                    writeFile file: 'publishProfile.publishSettings', text: AZURE_CREDENTIALS
                }

                // Use Maven deploy with publish profile
                sh '''
                    mvn azure-functions:deploy \
                        -Dazure.functions.deploy.type=publish-profile \
                        -Dazure.functions.publish.profile=publishProfile.publishSettings
                '''
            }
        }
    }

    post {
        always {
            echo 'Cleaning up sensitive files...'
            sh 'rm -f publishProfile.publishSettings'
        }
    }
}
