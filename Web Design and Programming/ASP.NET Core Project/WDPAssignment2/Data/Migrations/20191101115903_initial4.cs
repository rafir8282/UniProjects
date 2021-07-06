using Microsoft.EntityFrameworkCore.Migrations;

namespace WDPAssignment2.Data.Migrations
{
    public partial class initial4 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "Dislike",
                table: "MachineLearningCompaniesFeedback",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Dislike",
                table: "MachineLearningCompaniesFeedback");
        }
    }
}
