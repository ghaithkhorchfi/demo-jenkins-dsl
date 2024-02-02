multibranchPipelineJob('demo/demo_pipeline') {
    displayName("demo_pipeline")
    branchSources {
        branchSource {
            source {

                github {
// Specify the name of the GitHub Organization or GitHub User Account.
repoOwner("ghaithkhorchfi")
// The repository to scan.
repository("demotagpipeline")
// The server to connect to.
configuredByUrl(true)
// Specify the HTTPS URL of the GitHub Organization / User Account and repository.
repositoryUrl("https://github.com/ghaithkhorchfi/demotagpipeline.git")
// The behaviours control what is discovered from the GitHub repository.
                    traits {
                        gitHubBranchDiscovery {
                            // Determines which branches are discovered.
                            strategyId(3)
                        }
                        headWildcardFilter {
                            // Space-separated list of name patterns to consider.
                            includes("main develop PR-*")
                            // Space-separated list of name patterns to ignore even if matched by the includes list.
                            excludes("")
                        }
                        gitHubPullRequestDiscovery {
                            // Determines how pull requests are discovered.
                            strategyId(1)
                        }
                    }
}
            }
            strategy {
                defaultBranchPropertyStrategy {
                    props {
                        noTriggerBranchProperty{
                            triggeredBranchesRegex('^.*$')
                            strategy("INDEXING")
                        }
                    }
                }
            }
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(5)
            daysToKeep(14)
        }
    }
    factory {
        workflowBranchProjectFactory {
    	    scriptPath('Jenkinsfile')
        }
    }
}