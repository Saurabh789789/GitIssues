![](images/snip1.JPG)
![](images/snip2.JPG)

Input: github username
Output: stats for the user such as number of issues open etc.

The project uses github APIs to get the current open issues, closed issues etc for a given user. I deployed the code on heroku free account which might not work in future because the deployment is not a paid one. To test my application we can just use this code and deploy on any server. 
1)This is built using Rest Api's .
2)I have accessed git api's through its GitHub client.In client i have passed my authorization token to access the git services.
3)Inside the rest api i using am using git's Issue Service to call Git's "getIssues" api through GitHub client which we have access to
4)The other way to do is using 	HTTP client and executing a GET request 
5)I have used spring boot to create my application
6)I have used two files index.html and result.html to create the UI part and have integrated with backend
7)The code is well commented to understand
8)The code can be improved a lot in terms of fetching dat from github.The rest call can be made more specific to our requirement to reduce the number of calls to github api's.Also the request can be modified to get maximum data in one call to github api's rather than multiple calls.
9)The application has been deployed using heroku.
10)The sample input of application : https://github.com/Shippable/support/issues
