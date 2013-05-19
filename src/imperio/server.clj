(ns imperio.server
  (:use [org.httpkit.server]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET]])
  (:require [clojure.walk :as walk]
            [clojure.java.io :as io]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [cheshire.core :as json]
            [imperio.protocol :as imperio]
            [imperio.ip :as ip]))

(defn parse [s]
  (-> s
      json/parse-string
      walk/keywordize-keys))

;; Handlers

(defn ws-handler [request]
  (with-channel request ch
    (println ch "connected")
    (on-receive ch
                (fn [data]
                  ;(println data)
                  (imperio/execute! (parse data))))
    (on-close ch
              (fn [status]
                (println ch "closed" status)))))

;; Routes

(declare port)

(defroutes routes
  (GET "/" []
       {:headers {"Content-Type" "text/html"}
        :status  200
        :body    (slurp (io/resource "public/index.html"))})
  (GET "/socket" [:as request]
       (ws-handler request))
  (GET "/info" []
       {:headers {"Content-Type" "application/json"}
        :status  200
        :body    (json/generate-string
                  {:ip   (ip/first-ip-address)
                   :port @port})})
  (route/resources "/"))

;; Server

(defonce server (atom nil))
(defonce port (atom nil))

(defn start-server! [port]
  (reset! imperio.server/port port)
  (reset! server
          (run-server (site #'routes) {:port port})))

(defn stop-server! []
  (@server))
