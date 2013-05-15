(ns imperio.server
  (:use [org.httpkit.server])
  (:require [cheshire.core :as json]
            [imperio.protocol :as imperio]))

(defn parse [s]
  (-> s
      json/parse-string
      clojure.walk/keywordize-keys))

(defn ws-handler [request]
  (with-channel request ch
    (println ch "connected")
    (on-receive ch
                (fn [data]
                  (println data)
                  (imperio/execute! (parse data))))))

(defonce server (atom nil))

(defn start-server! []
  (reset! server
          (run-server #'ws-handler {:port 1410})))

(defn stop-server! []
  (@server))
