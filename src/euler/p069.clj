(ns euler.p069
  (:require [euler.numeric :as n]))

(defn- relatively-prime? [n f]
  (= (n/gcd n f) 1))

(defn- phi [n]
  (inc (count (filter #(relatively-prime? % n) (range 2 n)))))

(defn solution []
  (first
    (reduce (fn [[max-n max-n-over-phi] n]
              (let [n-over-phi (/ n (phi n))]
                (if (> n-over-phi max-n-over-phi)
                  [n n-over-phi]
                  [max-n max-n-over-phi])))
            [0 0]
            (range 2 1000001))))
