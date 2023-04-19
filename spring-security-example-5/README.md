In the example number five we are going to explore the basics of the authorization mechanism regarding access control

* Check the new code included in the class SecurityConfiguration.
  * Check the users, and their authorities.
  * Check that there is a lot of commented code, we are going to modify in live to see the main differences.
  * Run the application and try to navigate to localhost:8080/secured,  you must have a login redirect.
  * Log with any user, and repeat the before step, now it works.
  * Add the entrypoint and see the difference when try to go to localhost:8080/secured without login. (This is not about authorization)
  * Now add the has Authority(“WRITE”) and try to access with user Manuel. See the status code
  * Now add the access method and log with the user Andres.
  * And finally check the difference using hasRole option, in the user creation, why we use authorities at the same level?

