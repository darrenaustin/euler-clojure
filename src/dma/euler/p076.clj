(ns dma.euler.p076
  (:use clojure.contrib.def))

(defn-memo ways [n i]
  (cond (> i n) 0
        (= i n) 1
        :else (+ (#'ways (- n i) i)
                 (#'ways n (inc i)))))

(defn solution {:answer 190569291} []
  (dec (ways 100 1)))
