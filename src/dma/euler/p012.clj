(ns dma.euler.p012
  (:use dma.euler.numeric clojure.contrib.seq-utils))

(defn solution {:answer 76576500} []
  (find-first #(< 500 (count (divisors %))) (triangle-nums)))



