(ns typed-practice.core
  (require [clojure.core.typed :as t]))
;; REPL側で`(clojure.core.typed/check-ns 'typed-practice.core)`とやる

(t/ann x Long)
(def x 10)

; (t/ann y Integer)
; (def y 10)

(t/ann y Number)
(def y 10)

(t/ann z (U java.lang.Long java.lang.String (Value :z)))
(def z :z)

; (t/cf inc)
; (Fn [t/AnyInteger -> t/AnyInteger] [Number -> Number])

(t/ann f [Integer -> Integer])
(defn f [x] (int (inc x)))
;; これは怒ってくれる
;; (defn f [x] (inc x))

(t/ann g [Integer Integer -> Integer])
(defn g [x y] (int (+ x y)))

(t/ann count-words [(clojure.lang.Seqable String) -> t/AnyInteger])

(defn count-words [coll]
  (reduce (t/fn> [result :- t/AnyInteger
                  c :- String]
                 (+ result (count c)))
          0 coll))

(count-words ["aa" "aaa"])
;; core.typedがなかったら下の例はしれっと動いてしまう
;; 静的に型チェックをやって弾いてくれる
;; (count-words ["aa" ["aaa"]])

(t/ann count-words-in-docs [(clojure.lang.Seqable
                             (clojure.lang.Seqable String))
                            -> t/AnyInteger])

(defn count-words-in-docs [coll]
  (reduce (t/fn> [result :- t/AnyInteger
                  c :- (clojure.lang.Seqable String)]
                 (+ result (count-words c)))
          0 coll))

(count-words-in-docs [["a" "b"] ["b" "i"]])
;; (count-words-in-docs [["a" "b"] [[[1 2 3]]]])

