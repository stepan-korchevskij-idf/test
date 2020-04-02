pipeline {
  agent {label 'master'}

  triggers {
    cron('H 8,20 * * 1-5')
  }

  tools {
    jdk 'jdk8u172'
    gradle 'gradle-5.6'
  }

  options {
    timestamps()
    ansiColor('xterm')
    buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
  }

  stages {
    stage("Configure git") {
      steps {
        echo 'Configuring git...'
        checkout([
            $class                           : 'GitSCM',
            branches                         : [[name: '*/create-pipeline']],
            doGenerateSubmoduleConfigurations: false,
            extensions                       : [],
            submoduleCfg                     : [],
            userRemoteConfigs                : [[credentialsId: '6c925ca4-dc55-4120-b1f8-db0ea6232ff8', url: 'https://github.com/stepan-korchevskij-idf/test.git']]
        ])
      }
    }
    stage("Gradle clean") {
      steps {
        script {
          bat 'gradle clean'
        }
      }
    }
    stage("Run autotests") {
      steps {
        script {
          try {
            bat 'gradle headlessTest'
          } finally {
            publishHTML([
                reportDir            : "build/reports/tests/headlessTest/",
                reportFiles          : 'index.html',
                reportName           : "Gradle Test Report",
                reportTitles         : '',
                allowMissing         : true,
                alwaysLinkToLastBuild: true,
                keepAll              : true
            ])
          }
        }
      }
    }
  }

  post {
    always {
      script {
        junit allowEmptyResults: true, testResults: "build/test-results/**/*.xml"
        archiveArtifacts allowEmptyArchive: true, artifacts: "build/reports/**/*.*"
      }
    }
  }
}