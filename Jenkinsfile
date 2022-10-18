pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building..'
                sh './gradlew assemble'

            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
