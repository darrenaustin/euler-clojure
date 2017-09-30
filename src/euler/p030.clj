(ns euler.p030
  (:use euler.numeric))

(def fifth-powers (vec (map #(exp % 5) (range 10))))

(defn sum-of-digits-fifth [n]
  (sum (map fifth-powers (digits n))))

(defn solution {:answer 443839} []
  (sum
   (filter #(= (sum-of-digits-fifth %) %)
           (range 11 (* 6 (fifth-powers 9))))))
