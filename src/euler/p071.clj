(ns euler.p071)

(defn solution {:answer 428570} []
  (numerator
   (reduce (fn [closest d]
             (let [n (quot (* d 3) 7)
                   f (/ n d)]
               (if (< closest f 3/7)
                 f
                 closest)))
           0 (range 2 1000001))))


