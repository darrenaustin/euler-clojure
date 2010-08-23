(ns dma.euler.p025
  (:use dma.euler.numeric [clojure.contrib.seq-utils :only (indexed)]))

(defn solution {:answer 4782} []
  (ffirst (filter (fn [[i f]] (>= (num-digits f) 1000)) (indexed (fibs)))))
