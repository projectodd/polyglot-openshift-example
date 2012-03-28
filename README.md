TorqueBox and Immutant on OpenShift Express
===========================================

This git repository is an example of how to get Ruby applications on
top of TorqueBox and Clojure applications on top of Immutant up and
running on OpenShift Express. The .openshift directory contains the
necessary build action_hook to download TorqueBox and Immutant AS7
modules and their prerequisites, while the ruby and clojure
directories contain sample Ruby and Clojure applications.

Running on OpenShift
--------------------

Create an account at http://openshift.redhat.com/

Create a jbossas-7 application (using any application name you want)

    rhc app create -a polyglot -t jbossas-7

Add this upstream polyglot repository

    cd polyglot
    git remote add upstream -m master git://github.com/projectodd/polyglot-openshift-example.git
    git pull -s recursive -X theirs upstream master

Then push the repo upstream

    git push

That's it! Now you have simple Ruby and Clojure applications running
on top of TorqueBox and Immutant in OpenShift. The first deploy takes
a bit longer as the necessary libraries are downloaded to your
OpenShift instance, so grab a cup of coffee and after a minute or two
test that your Ruby application deployed correctly by going to

    http://polyglot-$yourdomain.rhcloud.com/ruby/

Verify you see a "Hello from Ruby!" page served. Test that your
Clojure application deployed correctly by going to

    http://polyglot-$yourdomain.rhcloud.com/clojure/

Verify you see a "Hello from Clojure!" message. If both of those
worked, try messaging between Ruby and Clojure by using the below
URLs:

    http://polyglot-$yourdomain.rhcloud.com/ruby/ping
    http://polyglot-$yourdomain.rhcloud.com/clojure/pong

    http://polyglot-$yourdomain.rhcloud.com/clojure/ping
    http://polyglot-$yourdomain.rhcloud.com/ruby/pong

The "/ruby/ping" URL publishes a message to a queue that
"/clojure/pong" receives from. Similarly, "/clojure/ping" publishes a
message to a different queue that "/ruby/pong" receives from.

Note that the sample Java application shipped with the jbossas-7
cartridge still works and is accessible via

    http://polyglot-$yourdomain.rhcloud.com/

Congratulations! You're up and running with Ruby and Clojure on
OpenShift.
