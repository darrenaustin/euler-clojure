(ns euler.p087
  (:use (euler numeric primes)))

;; TODO: figure out why this is suddenly causing out of memory exceptions
(defn solution- {:answer 1097343} []
  (let [target 50000000
        ps (primes)
        squares (take-while-< target (map square ps))
        cubes (take-while-< target (map cube ps))
        forths (take-while-< target (map #(* % % % %) ps))]
    (count
     (set
      (for [f forths c cubes s squares :let [sum (+ f c s)] :when (< sum target)]
        sum)))))
