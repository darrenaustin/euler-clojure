(ns euler.p026
  (:use euler.numeric))

(defn cycle-length [d]
  (loop [digits [], rems #{}, n 1]
    (if (or (zero? n) (rems n))
      (count digits)
      (recur (conj digits (quot (* 10 n) d))
             (conj rems n)
             (rem (* 10 n) d)))))

(defn solution {:answer 983} []
  (first
   (reduce (fn [m n] (if (> (second n) (second m)) n m))
           (map (fn [n] [n (cycle-length n)]) (range 1 1000)))))
