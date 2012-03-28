(ns pingpong.core
  (:use compojure.core)
  (:use [hiccup core page-helpers])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [immutant.web :as web]))

(defn view [msg]
  (html5
    [:head
      [:title "Ping Pong"]
      (include-css "css/openshift.css")]
    [:body
      [:h1 msg]]))

(defroutes main-routes
  (GET "/" [] (view "Hello from Clojure!"))
  (route/resources "/")
  (route/not-found "Page not found"))

(defn init []
  (web/start "/" (handler/site main-routes)))