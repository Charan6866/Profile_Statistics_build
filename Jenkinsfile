pipeline {
  agent any

  // adjust tool names to match your Jenkins tool configuration
  tools {
    maven 'Maven 3'    // change to your Maven installation name
    jdk 'JDK11'        // change to your JDK tool name
  }

  options {
    timestamps()
    timeout(time: 60, unit: 'MINUTES')
  }

  environment {
    MAVEN_OPTS = '-Xmx1g'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        script {
          if (isUnix()) {
            sh 'mvn -B -Dsurefire.suiteXmlFiles=testNg.xml clean test package'
          } else {
            bat 'mvn -B -Dsurefire.suiteXmlFiles=testNg.xml clean test package'
          }
        }
      }
    }

    stage('Publish Test Results') {
      steps {
        // publish Surefire / TestNG XML reports generated under target/surefire-reports
        junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
      }
    }

    stage('Allure Report') {
      when {
        expression { fileExists('allure-results') }
      }
      steps {
        // Uses Jenkins Allure plugin. If plugin is not available, replace with CLI generation in a shell step.
        allure([
          includeProperties: false,
          jdk: '',
          results: [[path: 'allure-results']]
        ])
      }
    }

    stage('Archive Artifacts & Reports') {
      steps {
        archiveArtifacts artifacts: 'target/*.jar, target/*.war, reports/**, screenshots/**, allure-results/**', fingerprint: true
      }
    }
  }

  post {
    success {
      echo 'Build succeeded'
    }
    failure {
      echo 'Build failed'
    }
    always {
      // requires Workspace Cleanup Plugin; optional
      cleanWs()
    }
  }
}
