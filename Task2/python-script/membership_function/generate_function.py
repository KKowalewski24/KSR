import sys


def generate_linear_function_equation(a, b, last=False):
    range_text = r" & \textrm{dla $x \in [" + a[0] + r", " + b[0]
    if last:
        range_text += r"]"
    else:
        range_text += r")"
    range_text += r"$}\\"
    if a[1] == b[1]:
        return a[1] + range_text
    else:
        wsp_a = (float(a[1]) - float(b[1])) / (float(a[0]) - float(b[0]))
        wsp_b = float(a[1]) - wsp_a * float(a[0])
        return str(round(wsp_a, 2)) + r"x + " + str(round(wsp_b, 2)) + range_text


def generate_equation(coordinates):
    points = []
    for i in range(0, len(coordinates), 2):
        points.append((coordinates[i], coordinates[i + 1]))

    if len(points) < 2:
        print("Należy podać współrzędne przynajmniej dwóch punktów")
    else:
        equation = r"\begin{displaymath}" + "\n"
        equation += "\t" + r"\mu_{" + sys.argv[1] + "}(x) = \left\{ \\begin{array}{ll}" + "\n"
        for i in range(len(points) - 2):
            equation += "\t\t" + generate_linear_function_equation(points[i], points[i + 1]) + "\n"
        equation += "\t\t" + generate_linear_function_equation(points[-2], points[-1], True) + "\n"
        equation += "\t" + r"\end{array} \right." + "\n"
        equation += r"\end{displaymath}"
    return equation


def main():
    with open(sys.argv[1] + ".txt", "w") as file:
        file.write(generate_equation(sys.argv[2:]))


if __name__ == "__main__":
    main()
