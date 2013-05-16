(ns imperio.server
  (:use [org.httpkit.server]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET]])
  (:require [compojure.route :as route]
            [ring.util.response :as resp]
            [cheshire.core :as json]
            [imperio.protocol :as imperio]))

(defn parse [s]
  (-> s
      json/parse-string
      clojure.walk/keywordize-keys))

;; Handlers

(defn ws-handler [request]
  (with-channel request ch
    (println ch "connected")
    (on-receive ch
                (fn [data]
                  (println data)
                  (imperio/execute! (parse data))))))

;; Routes

(defroutes routes
  (GET "/" []
       (resp/file-response "index.html" {:root "resources/public"}))
  (GET "/socket" [:as request]
       (ws-handler request))
  (route/resources "/"))


;; Server

(defonce server (atom nil))

(defn start-server! []
  (reset! server
          (run-server (site #'routes) {:port 1410})))

(defn stop-server! []
  (@server))
