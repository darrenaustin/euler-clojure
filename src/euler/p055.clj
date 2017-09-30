(ns euler.p055
  (:use euler.numeric))

(defn sum-with-reverse [n]
  (+' n (number (reverse (digits n)))))

(defn lychrel?
  ([n] (lychrel? n 0))
  ([n iters]
     (or (= iters 50)
         (let [next (sum-with-reverse n)]
           (and (not (palindrome? next))
                (lychrel? next (inc iters)))))))

(defn solution {:answer 249} []
  (count (filter lychrel? (range 1 10001))))
