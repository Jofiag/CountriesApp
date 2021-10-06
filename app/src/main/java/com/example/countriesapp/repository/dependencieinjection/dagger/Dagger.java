package com.example.countriesapp.repository.dependencieinjection.dagger;

public class Dagger {
    /*
    *   Dagger is a dependency injection.
    *   A dependency injection allow us to hide to the ViewModel the way the data is collected and let it know only how to use the data collected.
    *   So at the very core of a dependency injection is a library witch allows us to separate the instantiation to the use of an object.That help a lot in unit testing.
    *   Dagger2 :
    *       @Inject : tell Dagger witch object to inject
    *       @Module : tell Dagger how to create the object we want to inject
    *       @Provide : tell Dagger that this function is to instantiate or provide an object, the function is inside the module class
    *       @Component : tell Dagger how to leak the inject object  and it's module
    *
    *   Whenever we change the Dagger code, we need to rebuild the project so that the system generate the classes that perform the injection.
    *
    * */
}
