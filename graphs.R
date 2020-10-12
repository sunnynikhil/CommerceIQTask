#Read additions and deletions csv data
addtionsdeletionsData <- read.csv("/Users/oyo/NetBeansProjects/javacourse/src/CIQTask/additions_deletions.csv")


barplot(c(addtionsdeletionsData$a),names.arg=c(addtionsdeletionsData$w),main="Number of additions activity",xlab="weeks",ylab="Number of Additions",density=15)

barplot(c(addtionsdeletionsData$d),names.arg=c(addtionsdeletionsData$w),main="Number of Deletion activity",xlab="weeks",ylab="Number of Deletions")

#Read last year commit csv data
yearlyCommittedData <- read.csv("/Users/oyo/NetBeansProjects/javacourse/src/CIQTask/last_year_commit_activity.csv")

barplot(c(yearlyCommittedData$total),names.arg=c(yearlyCommittedData$week),main="Last Year Commit Activity ",xlab="weeks",ylab="Number of Commits",density=15)
