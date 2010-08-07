(ns dma.euler.p046
  (:use [dma.euler numeric primes]))

(defn solution {:answer 5777} []
  (let [ps (primes)]
    (first
     (for [n (iterate (partial + 2) 9)
           p ps
           :let [sq (Math/sqrt (/ (- n p) 2))]
           :while (not= sq (int sq))
           :when (< n p)]
       n))))

