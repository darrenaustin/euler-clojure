(ns euler.p035
  (:use [euler numeric primes]))

(defn solution {:answer 55} []
  (let [prime (apply sorted-set (take-while-< 1000000 (primes)))]
    (count
     (filter
      identity
      (for [p prime]
        (let [p-digits (vec (digits p))]
          (if (every? prime
                      (for [c (range 1 (count p-digits))]
                        (number (concat (subvec p-digits c) (subvec p-digits 0 c)))))
            p)))))))
