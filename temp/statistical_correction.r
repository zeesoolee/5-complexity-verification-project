
library(stats)

p_values = c(0.037619414, 0.00764104, 0.009821013, 0.188469991, 0.057483532, 0.107973801, 0.558885829, 0.015712099, 0.883170014, 0.78376723, 0.034554166, 0.080702143, 0.645775677, 0.519903617, 0.167920753, 0.35419549, 0.35419549, 0.070561369, 0.402754654, 0.51663738)

adj_p_vals = p.adjust(p_values, method = "holm")

print(adj_p_vals)

