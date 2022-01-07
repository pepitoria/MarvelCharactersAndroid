# Marvel Wiki App

Application that makes use of the marvel public api to display characters from the Marvel universe.

The app loads the first 100 characters from the api in the splash screen. Then you can see the
detail of one of the characters or search for one that is not on the list.

## Things to know

There is a specific tablet layout for the master detail that is different than the non-tablet
version.

UI Design wise, the app could be much better.

## Pattern used: MVVM.

## Tests: junit and android instrumentation tests.

- **junit:** just showcasing how could some logic be tested. It does not make a lot of sense because
  this app does not have a lot of logic.
- **Android instrumentation tests:** junit tests to test api calls and the code that handle the
  response to the marvel api.

## if you want to run it from code...

You will need to provide client_id and private_key for the marvel app as established in the
app/build.gradle in the production flavour.

## if you just want to install it...

just install it from
here: https://install.appcenter.ms/users/jlmontesj-41zb/apps/marvel-wiki-app/distribution_groups/public