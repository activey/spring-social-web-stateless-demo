How to use it:

1. Pull this code,
2. Modify src/main/resources/application.yml and type your LinkedIn application id and secret credentials,
3. Run DemoApplication.main()
4. Run curl in console: curl -X POST "http://localhost:8080/linkedin/signin" -v
5. Observe response "Location" header - open generated LinkedIn authentication url in web browser,
6. Log in into LinkedIn,
7. Have fun with stateless provider authentication :)