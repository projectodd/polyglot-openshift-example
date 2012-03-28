(ns pingpong.core
  (:use compojure.core)
  (:use [hiccup core page-helpers])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [immutant.web :as web]
            [immutant.messaging :as msg]))

(defn layout [& msgs]
  (html5
   [:head
    [:title "Ping Pong"]
    (include-css "css/openshift.css")]
   [:body
    [:h1 (apply str (interpose " " msgs))]
    [:p
     (link-to "/clojure" "clojure"): 
     (link-to "/clojure/ping" "ping")
     (link-to "/clojure/pong" "pong")]
    [:p
     (link-to "/ruby" "ruby"): 
     (link-to "/ruby/ping" "ping")
     (link-to "/ruby/pong" "pong")]]))

(defn ping []
  (let [msg (str "hello from clojure at " (System/currentTimeMillis))]
    (msg/publish "/queues/clojure" msg)
    (layout "Published" msg)))

(defn pong []
  (layout "Received" (msg/receive "/queues/ruby" :timeout 100)))

(defroutes main-routes
  (GET "/" [] (layout "Hello from Clojure!"))
  (GET "/ping" [] (ping))
  (GET "/pong" [] (pong))
  (route/resources "/")
  (route/not-found "Page not found"))

(defn init []
  (msg/start "/queues/ruby")
  (msg/start "/queues/clojure")
  (web/start "/" (handler/site main-routes)))