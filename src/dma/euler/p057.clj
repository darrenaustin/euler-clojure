(ns dma.euler.p057
  (:use dma.euler.numeric))

(defn solution {:answer 153} []
  (loop [f 1, bigger-numer 0, iter 0]
    (if (>= iter 1000)
      bigger-numer
      (let [next (+ 1 (/ 1 (+ 1 f)))
            numer (.numerator next)
            denom (.denominator next)]
        (if (> (count (digits numer)) (count (digits denom)))
          (recur next (inc bigger-numer) (inc iter))
          (recur next bigger-numer (inc iter)))))))
