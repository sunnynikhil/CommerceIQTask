#Read additions and deletions csv data
addtionsdeletionsData <- read.csv(Sys.getenv(Addition_Deletion_Activity_Filename))


barplot(c(addtionsdeletionsData$a),names.arg=c(addtionsdeletionsData$w),main="Number of additions activity",xlab="weeks",ylab="Number of Additions",border="black",col="blue",space = c(0.5),density=15)

barplot(c(addtionsdeletionsData$d),names.arg=c(addtionsdeletionsData$w),main="Number of Deletion activity",xlab="weeks",ylab="Number of Deletions",border="black",col="blue",space = c(0.5),density=15)

#Read last year commit csv data
yearlyCommittedData <- read.csv(Sys.getenv(Last_Year_Commit_Activity_Filename))

barplot(c(yearlyCommittedData$total),names.arg=c(yearlyCommittedData$week),main="Last Year Commit Activity ",xlab="weeks",ylab="Number of Commits",border="black",col="blue",space = c(0.5),density=15)
