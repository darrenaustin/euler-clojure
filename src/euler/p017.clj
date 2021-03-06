(ns euler.p017
  (:use euler.numeric euler.english))

(defn english-char [c]
  (not (or (= c \space) (= c \-))))

(defn num-english-chars [n]
  (count (filter english-char (english n))))

(defn solution {:answer 21124} []
  (sum (map num-english-chars (range 1 1001))))
