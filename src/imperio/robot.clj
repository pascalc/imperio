(ns imperio.robot
  (:use [imperio.keycodes :only [string->keycodes]])
  (:import [java.awt Robot MouseInfo]
           [java.awt.event InputEvent KeyEvent]))

;; Our robot

(def robot (Robot.))

;; Controlling the mouse

(defn set-mouse-position!
  "Set the mouse position to the given coordinates"
  [x y]
  (.mouseMove robot x y))

(defn mouse-position
  "Get the current coordinates of the cursor"
  []
  (let [position (.. (MouseInfo/getPointerInfo) getLocation)]
    [(.x position) (.y position)]))

(defn move-mouse!
  "Move the cursor by the relative amounts given"
  [rx ry]
  (let [[cx cy] (mouse-position)]
    (set-mouse-position! (+ cx rx) (+ cy ry))
    (mouse-position)))

(defn click!
  "Trigger a click event"
  [mask]
  (doto robot
    (.mousePress mask)
    (.mouseRelease mask)))

(def left-click!
  (partial click! InputEvent/BUTTON1_MASK))

(def right-click!
  (partial click! InputEvent/BUTTON2_MASK))

(defn double-click
  [clickfn]
  (dotimes [_ 2] (clickfn)))

(defn scroll!
  "Scroll the mouse wheel"
  [distance]
  (.mouseWheel robot distance))

;; Controlling the keyboard

(defn type!
  "Press and release the given list of keys"
  [keycodes]
  (doseq [kc keycodes]
    (doto robot
      (.keyPress kc)
      (.keyRelease kc))))

(defn tokenize
  [s]
  (drop 1 (clojure.string/split s #"")))

(defn type-string!
  "Convert the given string to a sequence
   of keycodes and type each one"
  [s]
  (let [tokens   (tokenize s)
        keycodes (flatten (map string->keycodes tokens))]
    (type! keycodes)))
