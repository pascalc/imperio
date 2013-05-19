;; Credit: http://software-ninja-ninja.blogspot.se/2013/05/clojure-what-is-my-ip-address.html

(ns imperio.ip
  (:import (java.net NetworkInterface Inet4Address)))

(defn ip-filter [inet]
  (and (.isUp inet)
       (not (.isVirtual inet))
       (not (.isLoopback inet))))

(defn ip-extract [netinf]
  (let [inets (enumeration-seq (.getInetAddresses netinf))]
    (map #(vector (.getHostAddress %1) %2) (filter #(instance? Inet4Address %) inets) (repeat (.getName netinf)))))

(defn ips []
  (let [ifc (NetworkInterface/getNetworkInterfaces)]
    (mapcat ip-extract (filter ip-filter (enumeration-seq ifc)))))

(defn ip-addresses []
  (map first (ips)))

(defn first-ip-address []
  (first (ip-addresses)))
