# DockerWebAutomation

DockerWebAutomation is an selenium based automation script which is integrated with docker image "selenoid" for optimum performace.
It is based on page object model pattern and supports extent reporting. 
The execution result part is also automated and is stored in single place with help of golang api and react frontend to display its content.

You may refer below project to see the implementations

Api:
https://github.com/ostandsouza/GoApi-CI

Web frontend:
https://github.com/ostandsouza/ReactHub

## Samples
These are few sample containing mongoDB sample data

### Testdata sample
This is how test data api looks like in database, after testdata api is called i.e once test script has executed successfully.

> db.dockerExpress.find()

{ "_id" : ObjectId("5d3f04825307204b2eb44a07"), "time" : "08:30:00", "suitename" : "DockerExpress", "device" : "opera", "notes" : "galen+selenium", "FailedTestCases" : "11", "PassedTestCases" : "1", "RunType" : "Smoke", "SkippedTestCases" : "0", "TotalTestCases" : "12", "functionalReport" : "5d3f0104405c424636b19713", "uiReport" : "5d3f02dd671e10db00ed29ae", "version" : "3.15" }

### TestResult sample
This is how test results api looks like in database, after test results api is called i.e once test script has executed successfully.

> db.file.files.find()

{ "_id" : ObjectId("5d30b1f3f728d55e3b84a1f9"), "length" : NumberLong(19), "chunkSize" : 261120, "uploadDate" : ISODate("2019-07-18T17:52:53.045Z"), "filename" : "Log_TS00099_2.txt", "metadata" : { "content-type" : "application/json" } }

## Authors
Ostan Dsouza - Initial work


