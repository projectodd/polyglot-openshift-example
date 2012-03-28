(ns pingpong.core
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(defroutes main-routes
  (GET "/" [] "<h1>Hello from Clojure!</h1>")
  (route/resources "/")
  (route/not-found "Page not found"))

(defn init []
  (web/start "/" (handler/site main-routes)))