(defproject pingpong "0.1.0-SNAPSHOT"
            :description "FIXME: write this!"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [compojure "1.0.1"]
                           [hiccup "0.3.7"]]
            :immutant {:init pingpong.core/init})

