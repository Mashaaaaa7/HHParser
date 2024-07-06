import { ReactNode } from "react";

import { ThemeProvider } from "components/theme-provider";

import "style/index.scss";

export default function RootLayout({
  children
}: {
  children: ReactNode
}) {
  return (
    <html lang="en">
      <head>
        <title>HH Parser</title>
      </head>
      <body>
        <ThemeProvider
          attribute="class"
          defaultTheme="dark"
          disableTransitionOnChange
        >
          {children}
        </ThemeProvider>
      </body>
    </html>
  );
}
