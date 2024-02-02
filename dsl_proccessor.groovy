properties([
    pipelineTriggers([
        githubPush()
    ])
])

pipeline {
    agent any
    
    stages {
        stage('Create job dsl') {
            steps {
                // All dsl files should follow this name
               jobDsl targets: ['folders.groovy','*/*.groovy'].join('\n'),
               // it will change modified jobs to match dsl script
               ignoreExisting: false,
               //if a job is deleted from the dsl script it will be deleted from jenkins
               removedJobAction: 'DELETE',
               // Runs the DSL scripts in a sandbox with limited abilities.
               // If unchecked, and you are not a Jenkins administrator, you will need to wait for an administrator to approve the scripts.
               sandbox: true
            }
        }
    }
}
