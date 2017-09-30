(ns euler.p033
  (:use euler.numeric))

(defn solution {:answer 100} []
  (.denominator
   (product
    (filter
     identity
     (for [n (range 11 99) d (range (inc n) 100) :when (and (not (div? n 10)) (not (div? d 10)))]
       (let [n1 (quot n 10) n2 (rem n 10)
             d1 (quot d 10) d2 (rem d 10)
             val (/ n d)]
         (when (or (and (= n2 d2) (= (/ n1 d1) val))
                   (and (= n1 d2) (= (/ n2 d1) val))
                   (and (= n2 d1) (= (/ n1 d2) val))
                   (and (= n1 d1) (= (/ n2 d2) val)))
           (/ n d))))))))

