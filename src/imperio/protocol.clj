(ns imperio.protocol
  (:require [imperio.robot :as robot]))

(def protocol
  {:move_mouse  robot/move-mouse!
   :right_click robot/right-click!
   :left_click  robot/left-click!
   :scroll      robot/scroll!
   :type        robot/type-string!})

(defn execute!
  [{command :command
    args    :args}]
  (println "Executing" command "with args" args)
  (apply
    (protocol (keyword command))
    args))
