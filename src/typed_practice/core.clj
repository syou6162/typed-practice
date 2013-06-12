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
