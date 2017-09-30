(ns euler.p027
  (:use [euler numeric primes]))

(defn quad [n a b]
  (+ (square n) (* a n) b))

(defn num-primes [a b primes]
  (count
   (take-while #(contains? primes (quad % a b)) (iterate inc 0))))

(defn solution {:answer -59231} []
  (let [primes (into #{} (take-while-< 10000 (primes)))]
    (second
     (reduce (fn [m n] (if (> (first n) (first m)) n m))
             (for [a (range -999 1000) b (range -999 1000)]
               [(num-primes a b primes) (* a b)])))))
