(ns imperio.main
  (:gen-class)
  (:require [imperio.ip :as ip]
            [imperio.server :as server])
  (:import  [java.awt Desktop]
            [java.net URI]))

(defn -main [& args]
  (let [ip   (ip/first-ip-address)
        port 4000
        dest (format "http://%s:%s/instructions.html"
                     ip port)]
    (server/start-server! port)
    (-> (Desktop/getDesktop)
        (.browse (URI. dest)))))
