/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./index.html", "./src/**/*.{js,jsx}"],
  theme: {
    extend: {
      colors: {
      'cream': "#d8ba9e",
      'forest': "#092014",
      'rangitoto': "#353423",
      'brown': "#694931",
      'armadillo': "#44443c",
      'everglade': "#264c2c",
      'offwhite': '#f0edea',
      'babygreen': "#afbc5c",
      'babyorange': "#de941d",
      'babyblue': "#85b5bf",
      'babygray': "#8f8b8d",
      'darkgray' : "#2c3030",
      'babypink' : "#f9d3c6",
      'pink': "#fce5fd"
      }
    },
    fontFamily: {
      poppins: ["Poppins", "sans-serif"],
      nunito: ["Nunito", "sans-serif"],
      raleway: ["Raleway", "sans-serif"]
    },
    fontSize: {
      sm: '0.8rem',
      base: '1rem',
      xl: '1.25rem',
      '2xl': '1.563rem',
      '3xl': '1.953rem',
      '4xl': '2.441rem',
      '5xl': '3.052rem',
    },
    screens: {
      xs: "480px",
      ss: "620px",
      sm: "768px",
      md: "1060px",
      lg: "1200px",
      xl: "1700px",
    },
  },
  plugins: [
    require('@tailwindcss/typography'),
  ],
}
