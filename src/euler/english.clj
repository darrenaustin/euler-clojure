(ns euler.english)

(declare english)

(def simple {
  0 "zero"
  1 "one"
  2 "two"
  3 "three"
  4 "four"
  5 "five"
  6 "six"
  7 "seven"
  8 "eight"
  9 "nine"
  10 "ten"
  11 "eleven"
  12 "twelve"
  13 "thirteen"
  14 "fourteen"
  15 "fifteen"
  16 "sixteen"
  17 "seventeen"
  18 "eighteen"
  19 "nineteen"
})

(def tens {
  2 "twenty"
  3 "thirty"
  4 "forty"
  5 "fifty"
  6 "sixty"
  7 "seventy"
  8 "eighty"
  9 "ninety"
})

(defn hundreds [n div label]
  (let [hundred (quot n div) rest (rem n div)]
    (str (english hundred) label
         (if (zero? rest) ""
             (str (if (< rest 100) " and " " ") (english rest))))))

(defn english
  "Returns the english phrase for the given number."
  [n]
  (cond (< n 20) (get simple n)
        (< n 100) (let [decade (quot n 10) rest (rem n 10)]
                    (str (get tens decade)
                         (if (zero? rest) "" (str "-" (english rest)))))
        (< n 1000) (hundreds n 100 " hundred")
        (< n 1000000) (hundreds n 1000 " thousand")
        (< n 1000000000) (hundreds n 1000000 " million")
        (< n 1000000000000) (hundreds n 1000000000 " billion")
        (< n 1000000000000000) (hundreds n 1000000000000 " trillion")
        :else (str "not supported: " n)))
