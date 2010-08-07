(ns dma.euler.p049
  (:use [dma.euler numeric primes]))

(defn same-digits [& ns]
  (apply = (map #(sort (digits %)) ns)))

(defn solution {:answer 296962999629} []
  (let [primes (apply sorted-set (take-between 1000 10000 (primes)))]
    (first
     (for [p1 primes
           p2 (drop-while-< (inc p1) primes)
           :let [p3 (+ (- p2 p1) p2)]
           :when (and (not= p1 1487)
                      (primes p3)
                      (same-digits p1 p2 p3))]
       (read-string (str p1 p2 p3))))))

