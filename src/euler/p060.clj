(ns euler.p060
  (:require [euler.numeric :as n]
            [euler.primes :exclude '[prime?] :as prime]
            [clojure.math.numeric-tower :as math]))

(def prime-vec (vec (n/take-while-< 10000 (prime/primes))))
(def prime-set (set prime-vec))

(def prime?
  (memoize
   (fn [n]
     (let [max-prime (last prime-vec)]
       (if (<= n max-prime)
         (not (nil? (prime-set n)))
         (let [max-factor (math/ceil (math/sqrt n))]
           (loop [ps prime-vec]
             (if-let [p (first ps)]
               (cond (n/div? n p) false
                     (> p max-factor) true
                     :else (recur (rest ps)))
               (every? identity (for [p (range max-prime max-factor 2)]
                                  (not (n/div? n p))))))))))))

(defn concat-prime? [a b]
  (and (prime? (Integer. (str a b)))
       (prime? (Integer. (str b a)))))

(defn concat-primes? [n candidates]
  (every? #(concat-prime? n %) candidates))

(defn search [matches candidates target-size]
  (if (= (count matches) target-size)
    matches
    (when-let [c (first candidates)]
      (if (concat-primes? c matches)
        (or (search (conj matches c) (rest candidates) target-size)
            (search matches (rest candidates) target-size))
        (recur matches (rest candidates) target-size)))))

(defn solution {:answer 26033} []
  (n/sum (search [] (rest prime-vec) 5)))
